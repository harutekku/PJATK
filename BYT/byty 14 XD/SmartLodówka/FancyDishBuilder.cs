using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class FancyDishBuilder : DishBuilder
    {
        Dish Dish;
        public FancyDishBuilder()
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
            Dish.Ingredients.Add(new Ingredients("Ser"));
            Dish.Ingredients.Add(new Ingredients("Ketchup"));
            Dish.Ingredients.Add(new Ingredients("Rzodkiewka"));
        }
    }
}
