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
        public List<LaneAssignment> LaneAssignment;
        public Lane(Warehouse warehouse, int id)
        {
            this.Warehouse = warehouse;
            Items = new Dictionary<string, Item>();
            this.LaneId = id;
            this.LaneAssignment = new List<LaneAssignment>();
        }
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
        public void setLaneAssignment(LaneAssignment LaneAssignment)
        {
            this.LaneAssignment.Add(LaneAssignment);
        }
        public void removeLaneAssignment(LaneAssignment laneAssignment)
        {
            LaneAssignment.Remove(laneAssignment);
        }
        public Warehouse GetWarehouse()
        {
            return Warehouse;
        }
        public int getLaneId()
        {
            return LaneId;
        }
    }
}
