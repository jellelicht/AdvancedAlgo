%read graph into laplacian matrix L
s = 'test2.txt';
s
%s = 'C:\Users\Chantal\Documents\MATLAB\Advanced algorithms\maxcut_5_10_10_instance_01';

fid = fopen(s);

tline = fgets(fid);
n = str2num(tline);
W = zeros(n,n);
while ischar(tline)
    tline = fgets(fid);
    if(tline(1) ~= -1) 
    %W(i,:) = str2num(tline);
    a = str2num(tline);
    W(a(1)+1,a(2)+1) = a(3);
    W(a(2)+1,a(1)+1) = a(3);
    end
end
fclose(fid);
%calculate the semi-definite solution
ops = sdpsettings('solver','sedumi');
Y = sdpvar(n,n);
%h = -1/4 *sum(sum(W*(1-Y)));
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
%h = -1/2 * (W(1,2)*(1-Y(1,2)) + W(1,3)*(1-Y(1,3)) + W(2,3)*(1-Y(2,3)));
c = [diag(Y) == ones(n,1), Y >=0];
sol = solvesdp(c, h, ops);
double(Y);
double(-h)
% sol.solvertime
y = double(Y);
[Q,A] = eig(y);
B = Q*sqrt(A);
%if isequal(y,B*B')
%fprintf('cholesky correct');
%end 
GW(B,W);





%We voldoen aan norm(B(i,:)) van i tot n


