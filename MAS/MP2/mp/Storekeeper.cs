using System;
using System.Collections.Generic;

namespace mp
{
    public class Storekeeper : Employee
    {
        private License license;
        private List<Warehouse> Warehouses;
        private List<LaneAssignment> LaneAssignments;
        
        public Storekeeper(string name, string surname, DateTime dateOfEmployment, License license) : base(name, surname, dateOfEmployment)
        {
            this.license = license;
            Warehouses = new List<Warehouse>();
            LaneAssignments = new List<LaneAssignment>();
        }
        public Storekeeper(string name, string surname, DateTime dateOfEmployment) : base(name, surname, dateOfEmployment)
        {
            this.license = new License();
            Warehouses = new List<Warehouse>();
            LaneAssignments = new List<LaneAssignment>();
        }
        
        //zwykła asocjacja
        public void addWarehouse(Warehouse warehouse)
        {
            if (!Warehouses.Contains(warehouse))
            {
                Warehouses.Add(warehouse);
                warehouse.addStorekeeper(this);
            }
        }
        public void removeWarehouse(Warehouse warehouse)
        {
            if (Warehouses.Contains(warehouse))
            {
                Warehouses.Remove(warehouse);
                warehouse.removeStorepeeker(this);
            }
        }

        //z atrybutem
        public void addLaneAssignment(LaneAssignment LaneAssignment)
        {
            if (!this.LaneAssignments.Contains(LaneAssignment))
            {
                this.LaneAssignments.Add(LaneAssignment);
            }
        }
        public LaneAssignment GetLastLaneAssignment()
        {
            return LaneAssignments[LaneAssignments.Count-1];
        }



        public override string ToString()
        {
            return base.ToString() + " and has license type " + license.LicenceType.ToString();
        }
    }
}
