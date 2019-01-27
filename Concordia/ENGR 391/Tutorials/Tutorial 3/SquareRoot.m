function X_solution = SquareRoot( p )

X_previous = p; imax = 20;

if p < 0.0

    disp('Error: function cannot compute negative input!')
    X_solution='Error';

else
    
    for i = 1:imax
        X_current = X_previous - (X_previous^2-p)/(2*X_previous);
        
        if absrelerror(X_current,X_previous) < 0.00001
            
            X_solution = X_current;
            break;
        end
        
        X_previous = X_current;
    end
    
    if (i == imax)
        
        fprintf('Solution was not obtained in first %i iterations', imax)
        X_solution = ('No answer');
    end
end

