using System;
using System.Collections.Generic;
using System.Text;

namespace mp
{
    public class LaneAssignment
    {
        Storekeeper storekeeper;
        Lane lane;
        DateTime dateFrom;
        DateTime dateTo;

        public LaneAssignment(Storekeeper storekeeper, Lane lane, DateTime from, DateTime to)
        {
            this.storekeeper = storekeeper;
            storekeeper.addLaneAssignment(this);
            this.lane = lane;
            lane.setLaneAssignment(this);
            this.dateFrom = from;
            this.dateTo = to;
        }

        public override string ToString()
        {
            return storekeeper.surname + " have shift to " + dateTo + " on lane "+lane.getLaneId();
        }
        public void removeAssignment()
        {
            storekeeper.removeLaneAssignment(this);
            lane.removeLaneAssignment(this);
        }
    }
}
