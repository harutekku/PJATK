using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Lane
    {
        private int LaneId;
        private Dictionary<string, Item> Items;
        private static HashSet<Item> ItemList = new HashSet<Item>();
        private Warehouse Warehouse;
        private List<LaneAssignment> LaneAssignments;
        public Lane(int id)
        {
            Items = new Dictionary<string, Item>();
            this.LaneId = id;
            this.LaneAssignments = new List<LaneAssignment>();
        }

        //z atrybutem
        public void addLaneAssignment(LaneAssignment LaneAssignment)
        {
            if (!this.LaneAssignments.Contains(LaneAssignment))
            {
                this.LaneAssignments.Add(LaneAssignment);
            }
        }

        //kwalifikowana
        public void setWarehouse(Warehouse warehouse)
        {
            if (warehouse.GetLane(LaneId).Equals(this))
            {
                this.Warehouse = warehouse;
            }
            else
            {
                throw new Exception("Cant add a warehouse which that doesnt belong to");
            }
        }

        //kompozycja
        public void addItem(Item item)
        {
            if (ItemList.Contains(item))
            {
                throw new Exception("Item already on lane");
            }
            else
            {
                ItemList.Add(item);
                Items.Add(item.name, item);
            }
        }
        public Item getItem(String name)
        {
            if (Items.ContainsKey(name))
            {
                return Items[name];
            }
            else throw new Exception("No item like this on that lane");
        }
        public void removeItem(String name)
        {
            if (Items.ContainsKey(name))
            {
                Item item = getItem(name);
                Items.Remove(name);
                ItemList.Remove(item);
                
            }
            else throw new Exception("No item like this on that lane");
        }

        
        
        public int getLaneId()
        {
            return LaneId;
        }

        public override string ToString()
        {
            return "Lane id "+LaneId+" with " + Items.Count+" items";
        }
    }
}
