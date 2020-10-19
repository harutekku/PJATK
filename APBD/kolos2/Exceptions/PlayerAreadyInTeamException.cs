using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Exceptions
{
    public class PlayerAreadyInTeamException : Exception
    {
        public PlayerAreadyInTeamException(string message) : base(message) { }
    }
}
