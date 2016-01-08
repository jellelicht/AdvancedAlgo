function lines = sdp(W);

tic
n = length(W);
Y = sdpvar(n,n);
lines = [];
tempMatrix = zeros(n,n);
theSum = 0;
for col = 1:n
    for row = 1:n
        if row >= col
            break;
        end
        theSum = theSum +(W(row,col)*(1-Y(row,col))); 
    end
end
h = -1/2*theSum;
c = [diag(Y) == ones(n,1), Y >=0];
ops = sdpsettings('solver','sedumi','verbose',0);
sol = solvesdp(c, h, ops);
UB = double(-h);
% sol.solvertime

y = double(Y);
[Q,A] = eig(y);
B = Q*sqrt(A);
%if isequal(y,B*B')
%fprintf('cholesky correct');
%end
preTime = toc

Ts = [1 5 10 25 50 75 100];
for t = Ts
    tic
    LB = GW(B,W,t);
    postTime = toc
    elapsed = preTime + postTime;
    pre = [LB, UB, elapsed];
    lines = [lines; pre];     
end
