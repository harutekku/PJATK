using kolos2.DTOs;
using kolos2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Services
{
    public interface IChampionshipService
    {
        public IEnumerable<Object> getTeamsByChampionship(int IdChampionship);
        public void addPlayerToTeam(AddPlayerRequest addPlayerRequest, int team);
    }
}
