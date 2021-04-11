using System;


namespace mp1
{
    class Storekeeper : Employee
    {
        License license;
        public override string ToString()
        {
            return base.ToString()+" and has license type "+license.LicenceType.ToString();
        }
        public Storekeeper(string name, string surname, DateTime dateOfEmployment, License license): base(name, surname, dateOfEmployment)
        {
            this.license = license;
        }
        public Storekeeper(string name, string surname, DateTime dateOfEmployment) : base(name, surname, dateOfEmployment)
        {
            this.license = new License();
        }
    }
}
