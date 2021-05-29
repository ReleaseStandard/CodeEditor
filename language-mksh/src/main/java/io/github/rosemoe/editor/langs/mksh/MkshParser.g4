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

start                : expr EOF ;
keyword              : CASE | ELSE | FUNCTION | THEN | DO | ESAC | IF | TIME | DONE | FI | IN | UNTIL | ELIF | FOR | SELECT | WHILE ;
expr                 : ( execution_control | instruction ) expr? ;
instruction          : TRUE (P_SEMI | TERMINATOR)? ;
execution_control    : for_do_done | if_then_else | select_in | until_do | while_do | function ;
for_do_done          : FOR IDENTIFIER (IN STRING*)? P_SEMI DO expr DONE ;
if_then_else         : IF expr P_SEMI THEN expr (ELIF expr P_SEMI THEN expr)* (ELSE expr)? FI ;
select_in            : SELECT IDENTIFIER (IN STRING*) P_SEMI DO expr DONE ;
until_do             : UNTIL expr P_SEMI DO expr DONE;
while_do             : WHILE expr P_SEMI DO expr DONE;
function             : FUNCTION IDENTIFIER P_L_PARENTHESIS (IDENTIFIER (P_COMMA IDENTIFIER)*)? P_R_PARENTHESIS P_L_BRACKET expr P_R_BRACKET;







