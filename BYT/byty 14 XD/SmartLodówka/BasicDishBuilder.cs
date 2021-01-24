using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class BasicDishBuilder : DishBuilder
    {
        Dish Dish;
        public BasicDishBuilder()
        {
            Dish = new Dish();
        }
        public Dish GetDish()
        {
            return Dish;
        }

        public void Pack()
        {
            Dish.Wrapping.TypeOfWrapping = "na talerzu";
        }

        public void Prepare(string name)
        {
            Dish.Name = name;
            Dish.Ingredients.Add(new Ingredients("Bułka"));
            Dish.Ingredients.Add(new Ingredients("Masło"));
            Dish.Ingredients.Add(new Ingredients("Szynka"));
        }
    }
}
