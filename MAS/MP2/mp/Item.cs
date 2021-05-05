using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Item
    {
        public string name { get; }
        float weight;
        Decimal price;
        Lane lane;
        private Item(string name, float weight, Decimal price, Lane lane)
        {
            this.name = name;
            this.weight = weight;
            this.price = price;
            this.lane = lane;
        }
        public static Item createItem(string name, float weight, Decimal price, Lane lane)
        {
            if(lane == null)
            {
                throw new Exception("Lane missing");
            }
            Item item = new Item(name, weight, price, lane);
            lane.addItem(item);
            return item;
        }
        public void setLine(Lane lane)
        {
            this.lane = lane;
        }
    }
}
