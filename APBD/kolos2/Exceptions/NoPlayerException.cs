using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Exceptions
{
    public class NoPlayerException : Exception
    {
        public NoPlayerException(string message) : base(message) { }
    }
}
