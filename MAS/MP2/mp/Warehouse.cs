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
        public void addLane()
        {
            Lanes.Add(Lanes.Count + 1, new Lane(this, Lanes.Count + 1)); //dwukierunkowa asocjacja
        }
        public void removeLane(int id)
        {
            foreach(LaneAssignment laneas in Lanes[id].LaneAssignment)
            {
                laneas.removeAssignment();
            }
            Lanes.Remove(id);
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
        public void removeStorepeeker(Storekeeper storekeeper)
        {
            Storekeepers.Remove(storekeeper);
            storekeeper.setWarehouse(null);
        }
    }
}
