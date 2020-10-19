using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Models
{
    public class Championship
    {
        public int IdChampionship { get; set; }
        public string OfficialName { get; set; }
        public int Year { get; set; }
        public IEnumerable<Championship_Team> championship_Teams{ get; set; }
        public Championship()
        {
            championship_Teams = new HashSet<Championship_Team>();
        }
    }
}
