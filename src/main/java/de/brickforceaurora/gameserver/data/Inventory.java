package de.brickforceaurora.gameserver.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.item.*;
import de.brickforceaurora.gameserver.item.template.TItem;

public class Inventory {

    public static final int MAX_ITEMS = 400;

    public int seq;

    public List<Item> equipment;
    public Item[] weaponChg;
    public Item[] shooterTools;
    public Item[] activeSlots;

    public String[] equipmentString;
    public String[] weaponChgString;

    /* ================= CONSTRUCTORS ================= */

    public Inventory(int seq) {
        this(seq, false);
    }

    public Inventory(int seq, boolean load) {
        this.seq = seq;
        this.equipment = new ArrayList<>();
        this.weaponChg = new Item[5];
        this.shooterTools = new Item[5];
        this.activeSlots = new Item[19];

        if (load) {
            loadInventoryFromDisk();
        }
    }

    /* ================= ITEM CREATION ================= */

    public Item createItem(
            TItem template,
            boolean sort,
            int amount,
            ItemUsage usage
    ) {
        if (equipment.size() >= MAX_ITEMS) return null;

        for (Item it : equipment) {
            if (it.getTemplate().code.equals(template.code))
                return null;
        }

        int seqSeed = seq + 1;

        byte[] baseSeq = new byte[8];
        byte[] seed = template.name.getBytes(StandardCharsets.UTF_8);
        byte[] codeSeed = template.code.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < seed.length && i < 5; i++) {
            baseSeq[i] = (byte) (seed[i] ^ seed[seed.length - 1 - i]);
        }

        for (int i = 0; i < 3 && i < codeSeed.length; i++) {
            baseSeq[i] ^= codeSeed[i];
        }

        long itemSeq = ByteBuffer.wrap(baseSeq)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getLong() * seqSeed;

        return new Item(
                itemSeq,
                template,
                template.code,
                usage,
                amount,
                (byte) 0,
                1000
        );
    }

    public Item addItem(
            TItem template,
            boolean sort,
            int amount,
            ItemUsage usage
    ) {
        Item item = createItem(template, sort, amount, usage);
        if (item == null) return null;

        equipment.add(item);

        if (sort) sort();

        return item;
    }

    /* ================= EQUIPMENT ================= */

    public void removeItem(Item item) {
        equipment.remove(item);
        updateActiveEquipment();
    }

    public void removeItem(long seq) {
        equipment.removeIf(x -> x.getSeq() == seq);
        updateActiveEquipment();
    }

    public void sort() {
        equipment.sort(Comparator.comparingInt(
                x -> x.getTemplate().slot.ordinal()
        ));
    }

    /* ================= WEAPON / TOOL SLOTS ================= */

    public void addWeaponSlot(long seq, byte slot) {
        Item item = findBySeq(seq, true);
        Item oldItem = findByToolSlot(slot, true);

        if (oldItem != null) oldItem.toolSlot = -1;
        if (item != null) item.toolSlot = slot;

        generateActiveChange();
    }

    private Item findBySeq(long seq, boolean weaponOnly) {
        for (Item it : equipment) {
            if (it.getSeq() == seq && (!weaponOnly || it.isWeaponSlotAble())) {
                return it;
            }
        }
        return null;
    }

    private Item findByToolSlot(byte slot, boolean weaponOnly) {
        for (Item it : equipment) {
            if (it.toolSlot == slot && (!weaponOnly || it.isWeaponSlotAble())) {
                return it;
            }
        }
        return null;
    }

    /* ================= ACTIVE GENERATION ================= */

    public void generateActiveSlots() {
        activeSlots = new Item[19];

        List<Item> activeItems = new ArrayList<>();
        for (Item it : equipment) {
            if (it.getUsage() == ItemUsage.EQUIP &&
                    it.getTemplate().type.ordinal() < ItemType.SPECIAL.ordinal()) {
                activeItems.add(it);
            }
        }

        equipmentString = new String[activeItems.size()];

        for (int i = 0; i < activeItems.size(); i++) {
            Item it = activeItems.get(i);
            equipmentString[i] = it.getCode();
            int index = slotToIndex(it.getTemplate().slot);
            if (index >= 0 && index < activeSlots.length) {
                activeSlots[index] = it;
            }
        }
    }

    public void generateActiveTools() {
        shooterTools = new Item[5];

        for (Item it : equipment) {
            if (it.isShooterSlotAble() && it.toolSlot >= 0 && it.toolSlot < shooterTools.length) {
                shooterTools[it.toolSlot] = it;
            }
        }
    }

    public void generateActiveChange() {
        weaponChg = new Item[5];

        List<Item> active = new ArrayList<>();
        for (Item it : equipment) {
            if (it.isWeaponSlotAble() && it.toolSlot >= 0) {
                active.add(it);
            }
        }

        weaponChgString = new String[active.size()];

        for (int i = 0; i < active.size() && i < weaponChg.length; i++) {
            weaponChgString[i] = active.get(i).getCode();
            weaponChg[active.get(i).toolSlot] = active.get(i);
        }
    }

    public void updateActiveEquipment() {
        generateActiveSlots();
        generateActiveTools();
        generateActiveChange();
    }

    /* ================= DISK IO (INTENTIONALLY STUBBED) ================= */

    public void save(String filePath) {
        try {
            updateActiveEquipment();
            // intentionally disabled like C#
        } catch (Exception e) {
            GameServerLogic.getInstance().logger().error("Save failed: " + e.getMessage());
        }
    }

    public void loadInventoryFromDisk() {
        // intentionally disabled like C#
    }

    public static int slotToIndex(ItemSlot slot) {
        return slot.value;
    }
}

