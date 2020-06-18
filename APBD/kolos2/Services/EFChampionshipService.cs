using kolos2.DTOs;
using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Services
{
    public class EFChampionshipService : IChampionshipService
    {
        private readonly DatabaseContext databaseContext;
        public EFChampionshipService(DatabaseContext databaseContext)
        {
            this.databaseContext = databaseContext;
        }
        public void addPlayerToTeam(Player player, int team)
        {
            throw new NotImplementedException(); //mało czasu :C 
        }

        public IEnumerable<object> getTeamsByChampionship(int IdChampionship)
        {
            var result = databaseContext.Championship_Team.Include(e => e.IdTeam).Where(e => e.IdChampionship == IdChampionship).Select(e => new TeamResponse
            {
                IdTeam = e.IdTeam,
                TeamName = e.Team.TeamName,
                MaxAge = e.Team.MaxAge,
                Score = e.Score
            }).ToList();
            if(result==null) throw new KeyNotFoundException();
            return result;
        }
    }
}
