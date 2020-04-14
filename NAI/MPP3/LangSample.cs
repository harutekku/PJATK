using System;

namespace MPP3
{
    public class LangSample
    {
        public double[] letters { get; set; }
        public string lang;
        public LangSample(string text, string lang)
        {
            this.lang = lang;
            text = text.ToLower();
            int length = 'z' - 'a' + 1;
            int counter = 0;
            letters = new double[length];
            Array.Clear(letters, 0, length);
            foreach (char c in text)
            {
                if (c >= 'a' && c <= 'z')
                {
                    letters[c - 'a']++;
                    counter++;
                }
            }
            for (int i = 0; i < length; i++)
            {
                letters[i] /= counter;
                letters[i] *= 1000.0;
            }
        }
    }
}
