/*
 [The "BSD licence"]
 Copyright (c) 2021 releasestandard@netc.eu
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/*
 * From the man pages here : https://linux.die.net/man/1/mksh
 * CAT_* are a basic parsing definition.
 */

parser grammar MkshParser; 
options {
	tokenVocab = MkshLexer;
}

start                : file ;
file                 : expr EOF ;
expr                 : ( ( arit | execution_control | instruction ) expression_end | comment | TERMINATOR+ ) expr? ;
instruction          : ( primary_keyword | secondary_keyword ) STRING* ;
expression_end       : P_SEMI | TERMINATOR ; 
comment              : LINE_COMMENT ;
identifier           : IDENTIFIER | primary_keyword | secondary_keyword ;
primary_keyword      : CASE | ELSE | FUNCTION | THEN | DO | ESAC | IF | TIME | DONE | FI | IN | UNTIL | ELIF | FOR | SELECT | WHILE ;
secondary_keyword    : BREAK | CONTINUE | EVAL | EXEC | EXIT | EXPORT | READONLY | RETURN | SET | SHIFT | TIMES | TRAP | UNSET | BUILTIN | GLOBAL |
                        TYPESET | WAIT | ALIAS | BG | BIND | CAT | CD | COMMAND | ECHO | FALSE | TRUE | FC | FG | GETOPTS | JOBS | KILL | LET |
                        MKNOD | PRINT | PWD | READ | REALPATH | RENAME | SLEEP | SUSPEND | TEST | ULIMIT | UMASK | UNALIAS | WHENCE ;

// execution flow control
execution_control    : for_do_done | if_then_else | select_in | until_do | while_do | function ;
for_do_done          : FOR identifier ( IN string* )? expression_end DO expr DONE ;
if_then_else         : IF expr THEN expr (ELIF expr THEN expr)* (ELSE expr)? FI ;
select_in            : SELECT identifier (IN string*) expression_end DO expr DONE ;
until_do             : UNTIL expr DO expr DONE;
while_do             : WHILE expr DO expr DONE;
function             : FUNCTION identifier P_L_PARENTHESIS (identifier (P_COMMA identifier)*)? P_R_PARENTHESIS P_L_BRACKET expr P_R_BRACKET;
string               : STRING ;

// arithmetic expression
arit                 : LET a_expr | ARIT_OPERATOR_L a_expr ARIT_OPERATOR_R;
a_immediate          : ARIT_ONE;
a_operand            : a_immediate | identifier;
a_expr               : a_expr a_operator_binary | a_expr a_operator_binary a_expr | a_operand;
a_operator_binary    : ARIT_A | ARIT_A_PLUS | ARIT_A_MINUS | ARIT_A_DIV | ARIT_A_MOD | ARIT_A_L_SHIFT | 
			ARIT_A_R_SHIFT | ARIT_A_L_ROTATE | ARIT_A_R_ROTATE | ARIT_A_XOR | ARIT_A_AND | ARIT_A_OR | ARIT_A_STAR | P_COMMA;
a_operator_unary     : ;





