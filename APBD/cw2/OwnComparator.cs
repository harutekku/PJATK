using System;
using System.Collections.Generic;
using System.Text;

namespace cw2
{
    public class OwnComparator : IEqualityComparer<Student>
    {
        public bool Equals(Student x, Student y)
        {
            return StringComparer.InvariantCultureIgnoreCase.Equals($"{x.Imie}{x.Nazwisko}{x.Eska},{y.Imie}{y.Nazwisko}{y.Eska}");
        }

        public int GetHashCode(Student x)
        {
            return StringComparer.CurrentCultureIgnoreCase.GetHashCode($"{x.Imie}{x.Nazwisko}{x.Eska}");
        }
    }
}
