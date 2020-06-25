using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Exceptions
{
    public class TooOldException : Exception
    {
        public TooOldException(string message) : base(message) { }
    }
}
