%%%-------------------------------------------------------------------
%%% @author razan
%%% @copyright (C) 2019, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 12. Aug 2019 2:37 AM
%%%-------------------------------------------------------------------
-module(math_server).
-author("razan").

%% API
-export([start/0,factorial/2,status/1,stop/1]).

loop(N)->
  receive
    {factorial,From,Ref,M} -> From ! {response, Ref, compute_factorial(M)},
      loop(N+1);
    {status,From,Ref} -> From ! {response,Ref, N},
      loop(N);
    {stop,_From,_Ref} ->
      ok
  end.


start()-> spawn(fun()-> loop(0) end).

factorial(S,M)-> Ref = make_ref(),
  S ! {factorial,self(),Ref,M},
  receive {response, Ref, Result} -> Result end.

status(S)->Ref = make_ref(),
  S ! {status,self(),Ref},
  receive {response, Ref, N} -> N end.

stop(S)-> S ! {stop ,self(),0}.



compute_factorial(0) -> 1;
compute_factorial(M) -> M * compute_factorial(M-1).
