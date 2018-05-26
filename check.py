#!/usr/bin/env python
#to execute immediately from terminal, make this file call the interpreter

from subprocess import call

call(["ant", "compile", "-q"])
call(["sh", "./readme.txt"])

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


with open("./output/tickets06.out", "r") as out_f, open("./public/cpu-i7/tickets06.out", "r") as ans_f:
    out_lines = out_f.readlines()
    ans_lines = ans_f.readlines()
    for i in range(1, len(out_lines)):
        if out_lines[i][0] == '[':
            if not comp_line(out_lines[i], ans_lines[i]):
                print(out_lines[i-1].rstrip('\n'))
                print("answer: " + str(ans_lines[i]) + "output: "+ str(out_lines[i]))
        elif out_lines[i][0] != '>':
            print(out_lines[i].rstrip('\n'))


print("\nnumber of errors: " + str(errcnt))


