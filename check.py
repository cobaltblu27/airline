#!/usr/bin/env python
#to execute immediately from terminal, make this file call the interpreter

from subprocess import call

call(["ant", "compile", "-q"])
call(["sh", "./readme.txt"])

errcnt = 0

def comp_line(str1, str2):
    global errcnt
    time1 = str1[-6:-2]
    time2 = str2[-6:-2]
    if time1 != time2:
        print("answer: " + str(str2) + "output: "+ str(str1))
        errcnt = errcnt + 1

with open("./output/tickets06.out", "r") as out_f, open("./public/cpu-i7/tickets06.out", "r") as ans_f:
    out_lines = out_f.readlines()
    ans_lines = ans_f.readlines()
    for i in range(1, len(out_lines)):
        if out_lines[i][0] == '[':
            comp_line(out_lines[i], ans_lines[i])
        elif out_lines[i][0] != '>':
            print(out_lines[i].rstrip('\n'))


print("\nnumber of errors: " + str(errcnt))


