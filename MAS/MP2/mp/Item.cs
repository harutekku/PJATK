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
        public Item(string name, float weight, Decimal price)
        {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }
        public void setLine(Lane lane)
        {
            this.lane = lane;
        }
    }
}
