using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    interface DishBuilder
    {
        void Prepare(string name);
        void Pack();
        Dish GetDish();
    }
}
