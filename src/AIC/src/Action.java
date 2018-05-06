package agent;

public enum Action {
	MonterenHaut,
	DescendreenBas,
	AlleraGauche,
	AlleraDroite,
	TirerenHaut,
	TirerenBas,
	TireraDroite,
	TireraGauche,
	
	
		public static Action Integer2Action(int x){
			switch(x) { // On evite les conditions type 'else is'
			case 0:
				return MonterenHaut;
			case 1:
				return DescendreenBas;
			case 2:
				return AlleraGauche;
			case 3:
				return AlleraDroite;    
			case 4:
				return TirerenHaut;
			case 5:
				return TirerenBas;
			case 6:
				return TireraDroite;
			case 7:
				return TireraGauche;      
			}
			return null;
    	}
	
		public static int Action2Integer(Action x) {
		switch(x) {
        case MonterenHaut:
            return 0;
        case DescendreenBas:
            return 1;
        case AlleraGauche:
            return 2;
        case AlleraDroite:
            return 3; 
        case TirerenHaut:
            return 4;
        case TirerenBas:
            return 5;
        case TireraDroite:
            return 6;
        case TireraGauche:
            return 7;      
        }
        return -1;
    } 
  
  
}
		