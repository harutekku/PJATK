using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Models
{
    public class Player_Team
    {
        public int IdPlayerTeam { get; set; }
        public int IdPlayer { get; set; }
        public int IdTeam { get; set; }
        public int NumOnShirt { get; set; }
        public string Comment { get; set; }
        public Player Player { get; set; }
        public Team Team { get; set; }
    }
}
