package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.channel.ClientReference;
import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.data.Inventory;
import de.brickforceaurora.gameserver.item.Item;
import de.brickforceaurora.gameserver.item.ItemType;
import de.brickforceaurora.gameserver.item.ItemUsage;
import de.brickforceaurora.gameserver.item.Pimp;
import de.brickforceaurora.gameserver.item.template.TItem;
import de.brickforceaurora.gameserver.item.template.TItemManager;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.protocol.MsgReference;

import java.util.ArrayList;
import java.util.List;

public class InventoryHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.EXT_OP_INVENTORY_ACK.id(), InventoryHandlers::inventoryData);
    }

    private static void inventoryData(GameServerLogic server, MsgReference msgRef)
    {
        // List to hold new equipment
        List<Item> newEquipment = new ArrayList<Item>();

        // Read the total count of items
        int itemCount = msgRef.msg.msg().readInt();
        msgRef.client.inventory = new Inventory(msgRef.client.seq);
        msgRef.client.inventory.equipment.clear();

        // Read each item's slot and code
        for (int i = 0; i < itemCount; i++)
        {
            String code = msgRef.msg.msg().readString();
            int usage = msgRef.msg.msg().readInt();
            byte toolSlot = msgRef.msg.msg().readByte(); //sbyte

            // Fetch the item template
            TItem template = TItemManager.getInstance().get(code);
            if (template != null)
            {
                try
                {
                    Item item = msgRef.client.inventory.addItem(template, false, -1, ItemUsage.fromId(usage));
                    //var item = msgRef.client.inventory.AddItem(template, false, -1, (Item.USAGE)Enum.Parse(typeof(Item.USAGE), usage, true));
                    item.toolSlot = toolSlot;
                }

                catch (Exception ex)
                {
                    server.logger().warning("HandleInventoryData: Couldn't add item " + template.name + " (" + template.code + ") | " + ex.getMessage());
                }
            }
            else
            {
                server.logger().warning("Template not found for code: {0}", code);
            }
        }

        server.logger().debug("HandleInventoryData from: {0}", msgRef.client.GetIdentifier());

        // Notify the client about the updated inventory
        SendInventory(server, msgRef.client);
    }

    public static void SendInventory(GameServerLogic server, ClientReference client)
    {
        client.inventory.updateActiveEquipment();
        SendItemList(server, client);
        SendShooterToolList(server, client);
        SendWeaponSlotList(server, client);
        //SendItemProperties(client);
        SendItemPimps(server, client);
        SendPremiumItems(server, client);
    }

    public static void SendItemList(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.inventory.equipment.size());
        for (int i = 0; i < client.inventory.equipment.size(); i++)
        {
            body.write(client.inventory.equipment.get(i).getSeq());
            body.write(client.inventory.equipment.get(i).getCode());
            body.write((byte) client.inventory.equipment.get(i).getUsage().id);
            body.write(client.inventory.equipment.get(i).getAmount());
            body.write(client.inventory.equipment.get(i).isPremium());
            body.write(client.inventory.equipment.get(i).getDurability());
        }

        server.say(new MsgReference(MessageId.CS_ITEM_LIST_ACK, body, client));

        server.logger().debug("SendItemList to: " + client.GetIdentifier());
    }

    public static void SendShooterToolList(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.inventory.shooterTools.length);
        for (int i = 0; i < client.inventory.shooterTools.length; i++)
        {
            if (client.inventory.shooterTools[i] == null)
            {
                body.write((byte)i);
                body.write((long)-1);
            }

            else
            {
                body.write(client.inventory.shooterTools[i].toolSlot);
                body.write(client.inventory.shooterTools[i].getSeq());
            }
        }

        server.say(new MsgReference(MessageId.CS_SHOOTER_TOOL_LIST_ACK, body, client));

        server.logger().debug("SendShooterToolList to: " + client.GetIdentifier());
    }

    public static void SendWeaponSlotList(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.inventory.weaponChg.length);
        for (int i = 0; i < client.inventory.weaponChg.length; i++)
        {
            if (client.inventory.weaponChg[i] == null)
            {
                body.write(i);
                body.write((long)-1);
            }

            else
            {
                body.write((int)client.inventory.weaponChg[i].toolSlot);
                body.write(client.inventory.weaponChg[i].getSeq());
            }
        }

        server.say(new MsgReference(MessageId.CS_WEAPON_SLOT_LIST_ACK, body, client));

        server.logger().debug("SendWeaponSlotList to: " + client.GetIdentifier());
    }

    public static void SendItemPimps(GameServerLogic server, ClientReference client) {

        List<Item> weapons = new ArrayList<>();

        for (Item item : client.inventory.equipment) {
            if (item.getTemplate().type == ItemType.WEAPON) {
                weapons.add(item);
            }
        }

        for (Item weapon : weapons) {
            sendItemPimp(server, client, weapon, Pimp.PROP_ATK_POW, 10);
            sendItemPimp(server, client, weapon, Pimp.PROP_ACCURACY, 10);
            sendItemPimp(server, client, weapon, Pimp.PROP_RECOIL, 10);
            sendItemPimp(server, client, weapon, Pimp.PROP_RPM, 10);
            sendItemPimp(server, client, weapon, Pimp.PROP_AMMO_MAX, 10);
            sendItemPimp(server, client, weapon, Pimp.PROP_ATTACK_SPEED, 10);
        }
    }


    public static void sendItemPimp(GameServerLogic server, ClientReference client, Item item, Pimp pimp, int grade)
    {
        try
        {
            if (!item.canUpgradeable())
                return;
        }
        catch (Exception ex) {
            return;
        }

        MsgBody body = new MsgBody();

        body.write(item.getSeq());
        body.write(pimp.getId());
        body.write(grade);

        server.say(new MsgReference(MessageId.CS_ITEM_PIMP_ACK, body, client));
    }

    public static void SendPremiumItems(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(2);
        body.write("s20");
        body.write("s21");

        server.say(new MsgReference(MessageId.CS_PREMIUM_ITEM_ACK, body, client));
    }
}
