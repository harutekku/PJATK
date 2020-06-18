using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Configurations
{
    public class Player_TeamConfiguration : IEntityTypeConfiguration<Player_Team>
    {
        public void Configure(EntityTypeBuilder<Player_Team> builder)
        {
            builder.HasKey(e => e.IdPlayerTeam)
                .HasName("IdPlayerTeam");

            builder.HasOne(d => d.Player)
                .WithMany(e => e.player_Teams)
                .HasForeignKey(d => d.IdPlayer)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("Player_Team_Player");

            builder.HasOne(d => d.Team)
                .WithMany(e => e.player_Teams)
                .HasForeignKey(d => d.IdTeam)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("Player_Team_Team");

            builder.Property(e => e.NumOnShirt)
                .HasMaxLength(300)
                .IsRequired();           
        }
    }
}
