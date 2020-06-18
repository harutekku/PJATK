using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Models
{
    public class Championship_Team
    {
        public int IdChampionshipTeam { get; set; }
        public int IdTeam { get; set; }
        public int IdChampionship { get; set; }
        public float? Score { get; set; }
        public Team Team { get; set; }
        public Championship Championship { get; set; }
    }
}
