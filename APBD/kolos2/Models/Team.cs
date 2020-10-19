using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Models
{
    public class Team
    {
        public int IdTeam { get; set; }
        public string TeamName { get; set; }
        public int MaxAge { get; set; }
        public IEnumerable<Player_Team> player_Teams { get; set; }
        public IEnumerable<Championship_Team> championship_Teams { get; set; }
        public Team()
        {
            player_Teams = new HashSet<Player_Team>();
            championship_Teams = new HashSet<Championship_Team>();
        }

    }
}
