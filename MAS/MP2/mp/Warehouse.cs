using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class Warehouse
    {
        private Dictionary<int, Lane> Lanes;
        private List<Storekeeper> Storekeepers;

        public Warehouse()
        {
            Lanes = new Dictionary<int, Lane>();
            Storekeepers = new List<Storekeeper>();
        }
        public void addLane()
        {
            Lanes.Add(Lanes.Count + 1, new Lane(this, Lanes.Count + 1));
        }
        public Lane GetLane(int id)
        {
            return Lanes[id];
        }
        public void addStorekeeper(Storekeeper storekeeper)
        {
            Storekeepers.Add(storekeeper);
            storekeeper.setWarehouse(this);
        }
    }
}
