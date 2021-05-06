using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Warehouse
    {
        private SortedDictionary<int, Lane> Lanes;
        private List<Storekeeper> Storekeepers;

        public Warehouse()
        {
            Lanes = new SortedDictionary<int, Lane>();
            Storekeepers = new List<Storekeeper>();
        }
        //zwykła asocjacja
        public void addStorekeeper(Storekeeper storekeeper)
        {
            if (!Storekeepers.Contains(storekeeper))
            {
                Storekeepers.Add(storekeeper);
                storekeeper.addWarehouse(this);
            }
        }
        public void removeStorepeeker(Storekeeper storekeeper)
        {
            if (Storekeepers.Contains(storekeeper))
            {
                Storekeepers.Remove(storekeeper);
                storekeeper.removeWarehouse(this);
            }
        }

        //kwalifikowana
        public void addLane(int id)
        {
            if (Lanes.ContainsKey(id))
            {
                throw new Exception("Lane with this number already exist");
            }
            else
            {
                Lanes.Add(id, new Lane(id));
                Lanes[id].setWarehouse(this);
            }
        }
        public Lane GetLane(int id)
        {
            if (Lanes.ContainsKey(id)) return Lanes[id];
            else throw new Exception("No Lane with this number");
        }
        public bool ContainsLane(int id)
        {
            return Lanes.ContainsKey(id);
        }
        public override string ToString()
        {
            return "Warehouse with "+Lanes.Count+" lanes";
        }
    }
}
