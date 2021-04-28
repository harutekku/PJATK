using System;
using System.Collections.Generic;

namespace mp
{
    public class Storekeeper : Employee
    {
        License license;
        Warehouse Warehouse;
        List<LaneAssignment> LaneAssignments;
        
        public Storekeeper(string name, string surname, DateTime dateOfEmployment, License license) : base(name, surname, dateOfEmployment)
        {
            this.license = license;
            LaneAssignments = new List<LaneAssignment>();
        }
        public Storekeeper(string name, string surname, DateTime dateOfEmployment) : base(name, surname, dateOfEmployment)
        {
            this.license = new License();
            LaneAssignments = new List<LaneAssignment>();
        }
        public override string ToString()
        {
            return base.ToString() + " and has license type " + license.LicenceType.ToString();
        }

        public void addLaneAssignment(LaneAssignment LaneAssignment)
        {
            LaneAssignments.Add(LaneAssignment);
        }
        public void setWarehouse(Warehouse warehouse)
        {
            this.Warehouse = warehouse;
        }
        public LaneAssignment GetLastLaneAssignment()
        {
            return LaneAssignments[LaneAssignments.Count-1];
        }



    }
}
