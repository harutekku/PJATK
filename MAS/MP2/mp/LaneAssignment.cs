using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class LaneAssignment
    {
        private Storekeeper storekeeper;
        private Lane lane;
        private DateTime dateFrom;
        private DateTime dateTo;

        public LaneAssignment(Storekeeper storekeeper, Lane lane, DateTime from, DateTime to)
        {
            this.storekeeper = storekeeper;
            storekeeper.addLaneAssignment(this);
            this.lane = lane;
            lane.addLaneAssignment(this);
            this.dateFrom = from;
            this.dateTo = to;
        }

        public override string ToString()
        {
            return storekeeper.surname + " have shift to " + dateTo + " on lane "+lane.getLaneId();
        }
    }
}
