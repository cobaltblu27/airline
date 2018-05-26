#!/usr/bin/env python
#to execute immediately from terminal, make this file call the interpreter
import subprocess
from subprocess import call
import argparse

parser = argparse.ArgumentParser(description="usage: [-c] -t [filenum]")
parser.add_argument("-c", dest="compile", default=False, action="store_true")
parser.add_argument("-t", dest="comp_index", default=0, type=int)

args = parser.parse_args()

if args.compile:
    call(["ant", "compile", "-q"])
    print("")

def main():
    if args.comp_index is 0:
        for i in range(1, 8):
            compare(i)
    else: 
        compare(args.comp_index)

errcnt = 0

def comp_line(str1, str2):
    global errcnt
    index = str1.rfind('>') + 1
    time1 = str1[index:index + 4]
    index = str2.rfind('>') + 1
    time2 = str2[index:index + 4]
    if time1 != time2:
        errcnt = errcnt + 1
        return False
    return True

def compare(index):
    output_dir = "./output/tickets0" + str(index) + ".out"
    answer_dir = "./public/cpu-i7/tickets0" + str(index) + ".out"
    
    if args.compile:
        with open("./readme.txt") as shfile:
            execute_line = shfile.readlines()
            with open(output_dir, "w") as outfile:
                subprocess.call(execute_line[index - 1].split(), stdout=outfile)

    with open(output_dir, "r") as out_f, open(answer_dir, "r") as ans_f:
        out_lines = out_f.readlines()
        ans_lines = ans_f.readlines()
        for i in range(1, len(out_lines)):
            if out_lines[i][0] == '[':
                if not comp_line(out_lines[i], ans_lines[i]):
                    print(out_lines[i-1].rstrip('\n'))
                    print("answer: " + str(ans_lines[i]) + "output: "+ str(out_lines[i]))
        for i in range(len(out_lines) - 4, len(out_lines)): 
            print(out_lines[i].rstrip('\n'))

        cputime_str = ans_lines[len(ans_lines) - 1]
        print("\nanswer CPU time" + cputime_str[cputime_str.rfind(':'):])
    print("number of errors: " + str(errcnt) + "\n")


main()


