using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Configurations
{
    public class Championship_TeamConfiguration : IEntityTypeConfiguration<Championship_Team>
    {
        public void Configure(EntityTypeBuilder<Championship_Team> builder)
        {
            builder.HasKey(e => e.IdChampionshipTeam)
                .HasName("IdChampionshipTeam");

            builder.HasOne(d => d.Team)
                .WithMany(e => e.championship_Teams)
                .HasForeignKey(d => d.IdTeam)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("Championship_Team_Team");

            builder.HasOne(d => d.Championship)
                .WithMany(e => e.championship_Teams)
                .HasForeignKey(d => d.IdChampionship)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("Championship_Team_Championship");

            builder.Property(e => e.Score)
                .IsRequired(false);
        }
    }
}
