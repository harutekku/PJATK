using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Item
    {
        public string name { get; private set; }
        private float weight;
        private Decimal price;
        private Lane lane;
        private Item(string name, float weight, Decimal price, Lane lane)
        {
            this.name = name;
            this.weight = weight;
            this.price = price;
            this.lane = lane;
        }
        //kompozycja
        public static Item createItem(string name, float weight, Decimal price, Warehouse warehouse, int lane)
        {
            if (warehouse.ContainsLane(lane))
            {
                Item item = new Item(name, weight, price, warehouse.GetLane(lane));
                item.lane.addItem(item);
                return item;
            }
            else
            {
                throw new Exception("No lane like that in this warehouse ");
            }
        }
        public override string ToString()
        {
            return name + ", " + weight + ", " + price +" and is on line "+lane.getLaneId();
        }
    }
}
