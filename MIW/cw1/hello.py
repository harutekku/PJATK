'''
Prosty skrypcik zeby byl na pionteczke
'''
import argparse

def main(args):
    '''
    Glowna funkcja tego modulu, printuje dzien dobry i wgl jest mila

    Parameters
    ----------
    args : argparse.Namespace
        Zawiera wszystko z uruchomienia z terminala

    Returns
    -------
    None.

    '''
    print("hello from the other side")
    print(f'Value of demo arg is {args.demo_argument}.')

def parse_arguments():
    '''
    Parsuje sobie argumenty

    Returns
    -------
    argspace.Namespace
        Nie zdazylem przepisac z ekranu

    '''
    parser=argparse.ArgumentParser(description='Other side')
    parser.add_argument('-d',
                        '--demo_argument',
                        type=str,
                        default='default_value',
                        help='demo argument')
    return parser.parse_args()

if __name__ == '__main__':
    main(parse_arguments())
