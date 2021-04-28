using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Lane
    {
        private int LaneId;
        private Dictionary<string, Item> Items;
        private Warehouse Warehouse;
        private LaneAssignment LaneAssignment;
        public Lane(Warehouse warehouse, int id)
        {
            this.Warehouse = warehouse;
            Items = new Dictionary<string, Item>();
            this.LaneId = id;
        }
        public void addItem(Item item)
        {
            if (Items.ContainsKey(item.name))
            {
                Items.Add(item.name, item);
                item.setLine(this);
            }
        }
        public void setLaneAssignment(LaneAssignment LaneAssignment)
        {
            this.LaneAssignment = LaneAssignment;
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
