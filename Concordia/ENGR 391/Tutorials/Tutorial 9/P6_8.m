TC = [-40 -20 0 20 40];
S =[0.0012 0.002 0.0032 0.006 0.0118];

[b, m] = ExpoFit(TC, S)

S10 = b*exp(m*10) %Estimated solubility at 10oC

%plotting actual actual data and estimated data together
Tp= -40:40;
Sp=b*exp(m*Tp);

plot(Tp, Sp, TC, S, '*')
%3 last arguments of plot function is used to place '*'
%at coordinates of actual data points
xlabel('Temp(C)')
ylabel('Water Solubility (%wt.)')