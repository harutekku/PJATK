using System.Collections.Generic;

namespace SmartLodówka
{
    public class Dish
    {
        public string Name;
        public Wrapping Wrapping;
        public List<Ingredients> Ingredients;
        public Dish()
        {
            Ingredients = new List<Ingredients>();
            Wrapping = new Wrapping();
        }
        public override string ToString()
        {
            string result = Name + " składająca się z";
            foreach (var item in Ingredients)
            {
                result += " "+item.Name + ",";
            }
            result = result.TrimEnd(',')+".\n";
            result += "Podana " + Wrapping.TypeOfWrapping;
            return result;
        }
    }
}