﻿using System;

namespace mp
{
    public class Employee : Person
    {
        DateTime dateOfEmployment;
        int EmploymentTime()
        {
            return (DateTime.Today - dateOfEmployment).Days;
        }

        public Employee(string name, string surname, DateTime dateOfEmployment)
        {
            this.name = name;
            this.surname = surname;
            this.dateOfEmployment = dateOfEmployment;
        }

        public override string ToString()
        {
            return name + " " + surname + " has been working here for " + EmploymentTime();
        }
    }
}
