% sdp
%   Calculate the lower bound, upper bound and running time for different GW randomized rounds
% Arguments
%   W - The adjacency matrix of the MAXCUT instance
function lines = sdp(W);


tic
n = length(W);
Y = sdpvar(n,n);
lines = [];

% see report for deriviation of this objective function
h = -1/4 * sum(dot(W,1-Y));
c = [diag(Y) == ones(n,1), Y >=0];
ops = sdpsettings('solver','sedumi','verbose',0);
sol = solvesdp(c, h, ops);
UB = double(-h);

y = double(Y);
[Q,A] = eig(y);
B = Q*sqrt(A);

% By this point, y should equal B*B'

% The time needed to calculate the SDP matrix B
preTime = toc

Ts = [1 5 10 25 50 75 100];
for t = Ts
    tic
    LB = GW(B,W,t);
    % Th time needed for the randomized GW rounds
    postTime = toc
    elapsed = preTime + postTime;
    pre = [LB, UB, elapsed];
    % Write each set of esults to the return value
    lines = [lines; pre];     
end
