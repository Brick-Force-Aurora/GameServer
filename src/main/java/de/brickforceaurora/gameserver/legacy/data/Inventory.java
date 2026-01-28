package de.brickforceaurora.gameserver.legacy.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.legacy.item.Item;
import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.ItemUsage;
import de.brickforceaurora.gameserver.legacy.item.template.TItem;

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

    public Inventory(final int seq) {
        this(seq, false);
    }

    public Inventory(final int seq, final boolean load) {
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

    public Item createItem(final TItem template, final boolean sort, final int amount, final ItemUsage usage) {
        if (equipment.size() >= MAX_ITEMS) {
            return null;
        }

        for (final Item it : equipment) {
            if (it.getTemplate().code.equals(template.code)) {
                return null;
            }
        }

        final int seqSeed = seq + 1;

        final byte[] baseSeq = new byte[8];
        final byte[] seed = template.name.getBytes(StandardCharsets.UTF_8);
        final byte[] codeSeed = template.code.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < seed.length && i < 5; i++) {
            baseSeq[i] = (byte) (seed[i] ^ seed[seed.length - 1 - i]);
        }

        for (int i = 0; i < 3 && i < codeSeed.length; i++) {
            baseSeq[i] ^= codeSeed[i];
        }

        final long itemSeq = ByteBuffer.wrap(baseSeq).order(ByteOrder.LITTLE_ENDIAN).getLong() * seqSeed;

        return new Item(itemSeq, template, template.code, usage, amount, (byte) 0, 1000);
    }

    public Item addItem(final TItem template, final boolean sort, final int amount, final ItemUsage usage) {
        final Item item = createItem(template, sort, amount, usage);
        if (item == null) {
            return null;
        }

        equipment.add(item);

        if (sort) {
            sort();
        }

        return item;
    }

    /* ================= EQUIPMENT ================= */

    public void removeItem(final Item item) {
        equipment.remove(item);
        updateActiveEquipment();
    }

    public void removeItem(final long seq) {
        equipment.removeIf(x -> x.getSeq() == seq);
        updateActiveEquipment();
    }

    public void sort() {
        equipment.sort(Comparator.comparingInt(x -> x.getTemplate().slot.ordinal()));
    }

    /* ================= WEAPON / TOOL SLOTS ================= */

    public void addWeaponSlot(final long seq, final byte slot) {
        final Item item = findBySeq(seq, true);
        final Item oldItem = findByToolSlot(slot, true);

        if (oldItem != null) {
            oldItem.toolSlot = -1;
        }
        if (item != null) {
            item.toolSlot = slot;
        }

        generateActiveChange();
    }

    private Item findBySeq(final long seq, final boolean weaponOnly) {
        for (final Item it : equipment) {
            if (it.getSeq() == seq && (!weaponOnly || it.isWeaponSlotAble())) {
                return it;
            }
        }
        return null;
    }

    private Item findByToolSlot(final byte slot, final boolean weaponOnly) {
        for (final Item it : equipment) {
            if (it.toolSlot == slot && (!weaponOnly || it.isWeaponSlotAble())) {
                return it;
            }
        }
        return null;
    }

    /* ================= ACTIVE GENERATION ================= */

    public void generateActiveSlots() {
        activeSlots = new Item[19];

        final List<Item> activeItems = new ArrayList<>();
        for (final Item it : equipment) {
            if (it.getUsage() == ItemUsage.EQUIP && it.getTemplate().type.ordinal() < ItemType.SPECIAL.ordinal()) {
                activeItems.add(it);
            }
        }

        equipmentString = new String[activeItems.size()];

        for (int i = 0; i < activeItems.size(); i++) {
            final Item it = activeItems.get(i);
            equipmentString[i] = it.getCode();
            final int index = slotToIndex(it.getTemplate().slot);
            if (index >= 0 && index < activeSlots.length) {
                activeSlots[index] = it;
            }
        }
    }

    public void generateActiveTools() {
        shooterTools = new Item[5];

        for (final Item it : equipment) {
            if (it.isShooterSlotAble() && it.toolSlot >= 0 && it.toolSlot < shooterTools.length) {
                shooterTools[it.toolSlot] = it;
            }
        }
    }

    public void generateActiveChange() {
        weaponChg = new Item[5];

        final List<Item> active = new ArrayList<>();
        for (final Item it : equipment) {
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

    public void save(final String filePath) {
        try {
            updateActiveEquipment();
            // intentionally disabled like C#
        } catch (final Exception e) {
            GameServerApp.logger().error("Save failed: " + e.getMessage());
        }
    }

    public void loadInventoryFromDisk() {
        // intentionally disabled like C#
    }

    public static int slotToIndex(final ItemSlot slot) {
        return slot.value;
    }
}
