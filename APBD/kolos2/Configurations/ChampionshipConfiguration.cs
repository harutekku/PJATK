using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Configurations
{
    public class ChampionshipConfiguration : IEntityTypeConfiguration<Championship>
    {
        public void Configure(EntityTypeBuilder<Championship> builder)
        {
            builder.HasKey(e => e.IdChampionship)
                .HasName("IdChampionship");

            builder.Property(e => e.OfficialName)
                .HasMaxLength(100)
                .IsRequired();

            builder.Property(e => e.Year)
                .IsRequired();
        }
    }
}