using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Configurations
{
    public class TeamConfiguration : IEntityTypeConfiguration<Team>
    {
        public void Configure(EntityTypeBuilder<Team> builder)
        {
            builder.HasKey(e => e.IdTeam)
                .HasName("IdTeam");

            builder.Property(e => e.TeamName)
                .HasMaxLength(30)
                .IsRequired();

            builder.Property(e => e.MaxAge)
                .IsRequired();
        }
    }
}
