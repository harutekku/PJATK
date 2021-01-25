using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class Packer
    {
        private DishBuilder dishBuilder;

        public Packer(DishBuilder builder)
        {
            dishBuilder = builder;
        }

        public Dish createDish(Contents Contents, string name)
        {
            dishBuilder.Prepare(Contents, name);
            dishBuilder.Pack();
            return dishBuilder.GetDish();
        }
    }
}
