using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class FancyDishBuilder : DishBuilder
    {
        Dish Dish;
        public Dish GetDish()
        {
            return Dish;
        }

        public void Pack()
        {
            Dish.Wrapping.TypeOfWrapping = "na talerzu";
        }

        public void Prepare(Contents Contents, string name)
        {
            Dish = new Dish();
            Dish.Name = name;
            List<Ingredients> list = new List<Ingredients>();
            list.Add(new Ingredients("Bułka"));
            list.Add(new Ingredients("Masło"));
            list.Add(new Ingredients("Szynka"));
            list.Add(new Ingredients("Ser"));
            list.Add(new Ingredients("Ketchup"));
            list.Add(new Ingredients("Rzodkiewka"));
            if (Contents.prepareIngredients(list))
            {
                foreach (var item in list)
                {
                    Dish.Ingredients.Add(item);
                }
            }
            else
            {
                throw new Exception("Brakuje składników");
            }
        }
    }
}
