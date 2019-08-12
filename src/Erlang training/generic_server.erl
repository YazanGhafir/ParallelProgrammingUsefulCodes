%%%-------------------------------------------------------------------
%%% @author razan
%%% @copyright (C) 2019, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 12. Aug 2019 3:55 AM
%%%-------------------------------------------------------------------
-module(generic_server).
-author("razan").

%% API
-export([start/0, factorial/2, status/1, update/2, stop/1]).

%server's loop function
loop(State,Handler)->
  receive

    %request handler for request-type messages
    {request, From, Ref, Request} ->
      case catch(Handler(State,Request)) of

        %exception-handling for executing the handler function thus extending the server's robustness
        {'Exit', Reason} ->
          From ! {error, Ref, Reason},
          loop(State,Handler);

        %the reply from the handler
        {reply, NewState, Result} ->
          From ! {response, Ref, Result},
          loop(NewState,Handler)

      end;

    %handling a message to update the handler function for extending the flexibility
    {update, From, Ref, NewHandler} ->
      From ! {ok, Ref}, %ack
      loop(State, NewHandler);

    %handling a message to shut down the server
    {stop,_From,_Ref} -> ok;

    %handling any other junk message to not stay in the mail box
    _ -> loop (State, Handler)

  end.

%request sender
request(Server, Request)->
  Ref = make_ref(),
  Server ! {request, self(), Ref, Request},
  receive
    {response, Ref, Result} -> Result
  end.



%API functions ************
start() -> start(0, fun math_handler/2).
start(InitialState,Handler)-> spawn(fun()-> loop(InitialState,Handler) end).

%**request function:**
factorial(Server,M) -> request(Server,{factorial,M}).
status(Server) -> request(Server,status).
%****

update(Server,NewHandler) ->Ref = make_ref(), Server ! {update, self(), Ref, NewHandler}, receive {ok, Ref} -> ok end.
stop(S)-> S ! {stop ,self(),0}, ok.
%*****************************


%handler's functionality
math_handler(N, {factorial, M}) -> {reply, N+1, compute_factorial(M)};
math_handler(N, status) -> {reply, N, N}.

%compute_factorial function
compute_factorial(0) -> 1;
compute_factorial(M) -> M * compute_factorial(M-1).

