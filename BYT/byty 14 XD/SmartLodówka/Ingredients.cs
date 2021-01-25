using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    public class Ingredients : IComparer
    {
        public string Name;
        public Ingredients(string Name) => this.Name = Name;

        public int Compare(object x, object y)
        {
            Console.WriteLine("dupa");
            return ((Ingredients)x).Name.Equals(((Ingredients)y).Name)?0:1;
        }
    }
}
