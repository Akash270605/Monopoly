/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_interface_adapters;

import game_entities.Player;
import game_entities.tiles.Property;
import turn_use_cases.trade_use_case.TradeOffer;
import turn_use_cases.trade_use_case.TradeOption;
import turn_use_cases.trade_use_case.TradeOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TradePresenter implements TradeOutputBoundary{
    
    private final TurnController turnController;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final JPanel optionsPanel;
    
    public TradePresenter(JPanel mainPanel, CardLayout cardLayout, TurnController turnController){
        this.turnController = turnController;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.optionsPanel = new JPanel();
        this.optionsPanel.setLayout(cardLayout);
        mainPanel.add(optionsPanel, "Trade Panel");
    }
    
    //Presenter the list of players player can trade with 
    @Override
    public void showListOfPlayers(ArrayList<Player> listOfPlayers, Player player, String flavorText){
        optionsPanel.removeAll();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.add(new JLabel(flavorText + "      "));
        
        
        for(Player otherPlayer : listOfPlayers){
            JButton option = new JButton("Pick " + otherPlayer.getName());
            
            option.addActionListener(e -> turnController.startTrade(player, otherPlayer));
            optionsPanel.add(option);
        }
        
        JButton cancelTrade = new JButton("Cancel Trade");
        optionsPanel.add(cancelTrade);
        cancelTrade.addActionListener(e -> turnController.endUseCase());
        
        optionsPanel.validate();
        optionsPanel.repaint();
        cardLayout.show(mainPanel, "Trade Panel");
    }
    
    //Presenter the possible options for player1 to trade with player2
    @Override
    public void showTradeOptions(TradeOption tradeOption, String flavorText){
        optionsPanel.removeAll();
        optionsPanel.setLayout(new GridLayout(1, 6));
        optionsPanel.add(new JLabel(flavorText));
        ArrayList<String> player1PropertiesDisplay = new ArrayList<>();
        ArrayList<Property> player1Properties = new ArrayList<>();
        
        for(Property property : tradeOption.getPlayer1Properties()){
            player1PropertiesDisplay.add(property.getTileDisplayName());
            player1Properties.add(property);
        }
        
        ArrayList<String> player2PropertiesDisplay = new ArrayList<>();
        ArrayList<Property> player2Properties = new ArrayList<>();
        
        for(Property property : tradeOption.getPlayer2Properties()){
            player2PropertiesDisplay.add(property.getTileDisplayName());
            player2Properties.add(property);
        }
        
        ArrayList<Property> propertiesOffered = new ArrayList<>();
        ArrayList<Property> propertiesReceived = new ArrayList<>();
        
        JList<Object> listP1Properties = new JList<>(player1PropertiesDisplay.toArray());
        listP1Properties.setVisible(true);
        listP1Properties.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane  propertiesOfferedList = new JScrollPane(listP1Properties);
        
        JList<Object> listP2Properties = new JList<>(player2PropertiesDisplay.toArray());
        listP2Properties.setVisible(true);
        listP2Properties.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane propertiesRequestedList = new JScrollPane(listP2Properties);
        
        optionsPanel.setBorder(BorderFactory.createTitledBorder("List"));
        optionsPanel.add(propertiesOfferedList);
        
        JLabel propertiesOfferedText  = new JLabel("<html><body>" + "Properties offered to "+
                tradeOption.getPlayer2().getName() + "</body></html>");
        
        optionsPanel.add(propertiesOfferedText);
        optionsPanel.add(propertiesRequestedList);
        
        JLabel propertiesRequestedText = new JLabel("<html><body>"+ "Properties requested from " + tradeOption.getPlayer2().getName() + "</body></html>" );
        
        optionsPanel.add(propertiesRequestedText);
        
        JPanel tradeMoneyPanel = new JPanel();
        tradeMoneyPanel.setLayout(new BoxLayout(tradeMoneyPanel, BoxLayout.Y_AXIS));
        tradeMoneyPanel.add(new JLabel("<html><body>" + "Money offered to " + tradeOption.getPlayer2().getName() + "</body></html>"));
        
        final int[] tradeMoney = {0};
        final int[] tradeMoneyOffered = {0};
        final int [] tradeMoneyReceived ={0};
        
        JFormattedTextField tradeMoneyOfferedField = new JFormattedTextField(NumberFormat.getNumberInstance());
        tradeMoneyOfferedField.setEditable(true);
        tradeMoneyOfferedField.setValue(0);
        tradeMoneyOfferedField.setColumns(15);
        
        tradeMoneyPanel.add(tradeMoneyOfferedField);
        
        tradeMoneyPanel.add(new JLabel("<html><body>"+"Money requested from " + tradeOption.getPlayer2().getName() +
                "</body></html>"));
        
        JFormattedTextField tradeMoneyReceivedField = new JFormattedTextField(NumberFormat.getNumberInstance());
        tradeMoneyReceivedField.setEditable(true);
        tradeMoneyReceivedField.setValue(0);
        tradeMoneyReceivedField.setColumns(15);
        
        tradeMoneyPanel.add(tradeMoneyReceivedField);
        
        optionsPanel.add(tradeMoneyPanel);
        
        JRadioButton noJailCard = new JRadioButton("<html><body>" +"No Jail Card"+ "</body></html>");
        JRadioButton offerJailCard = new JRadioButton("<html><body>" +"Offer Jail Card"+"</body></html>");
        JRadioButton requestJailCard = new JRadioButton("<html><body>"+"Request Jail Card"+"</body></html>");
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(noJailCard);
        buttonGroup.add(offerJailCard);
        buttonGroup.add(requestJailCard);
        
        JPanel jailButtons = new JPanel();
        jailButtons.setLayout(new BoxLayout(jailButtons, BoxLayout.Y_AXIS));
        jailButtons.setSize(1, 3);
        jailButtons.add(noJailCard);
        jailButtons.add(offerJailCard);
        jailButtons.add(requestJailCard);
        optionsPanel.add(jailButtons);
        
        final int[] jailCard = {0};
        
        JPanel endPanel = new JPanel();
        endPanel.setLayout(new GridLayout(2, 1));
        
        JButton submit = new JButton("<html><body>" + "Submit Offer"+"</body></html>");
        endPanel.add(submit);
        
        JButton cancelTrade = new JButton("<html><body>"+"<Cancel Trade"+"</body></html>");
        endPanel.add(cancelTrade);
        
        optionsPanel.add(endPanel);
        
        noJailCard.addActionListener(e -> jailCard[0] = 0);
        offerJailCard.addActionListener(e -> jailCard[0] = 1);
        requestJailCard.addActionListener(e -> jailCard[0] = -1);
        
        tradeMoneyOfferedField.addPropertyChangeListener(evt -> tradeMoneyOffered[0] = ((Number) tradeMoneyOfferedField.getValue()).intValue());
        tradeMoneyReceivedField.addPropertyChangeListener(evt -> tradeMoneyReceived[0] = ((Number) tradeMoneyReceivedField.getValue()).intValue());
        
        submit.addActionListener(e -> {
            tradeMoney[0] = tradeMoneyOffered[0] - tradeMoneyReceived[0];
            TradeOffer tradeOffer = new TradeOffer(tradeMoney[0], jailCard[0],
                    propertiesOffered, propertiesReceived, tradeOption.getPlayer1(), tradeOption.getPlayer2());
            
            turnController.makeOffer(tradeOption.getPlayer1(), tradeOption.getPlayer2(), tradeOffer);
        });
        
        cancelTrade.addActionListener(e -> turnController.endUseCase());
        
        listP1Properties.addListSelectionListener(e ->{
            propertiesOffered.clear();
            List<Object> SelectedProperties = listP1Properties.getSelectedValuesList();
            for(Object propString: SelectedProperties){
                int x = player1PropertiesDisplay.indexOf(propString);
                propertiesOffered.add(player1Properties.get(x));
            }
        });
        
        listP2Properties.addListSelectionListener(e ->{
            propertiesReceived.clear();
            List<Object> SelectedProperties = listP2Properties.getSelectedValuesList();
            for(Object propString: SelectedProperties){
                int x = player2PropertiesDisplay.indexOf(propString);
                propertiesReceived.add(player2Properties.get(x));
            }
        });
        
        optionsPanel.validate();
        optionsPanel.repaint();
        cardLayout.show(mainPanel, "Trade Panel");
    }
    
    //Present the trade Offer
    @Override
    public void showTradeOffer(TradeOffer tradeOffer, String flavorText){
        optionsPanel.removeAll();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        
        if(!tradeOffer.isValid()){
            optionsPanel.add(new JLabel(flavorText));
            JButton newOffer = new JButton("New Offer");
            optionsPanel.add(newOffer);
            newOffer.addActionListener(e -> turnController.startTrade(tradeOffer.getPlayer1(), tradeOffer.getPlayer2()));
        }else{
            Player player1 = tradeOffer.getPlayer1();
            Player player2 = tradeOffer.getPlayer2();
            String player1Name = player1.getName();
            
            StringBuilder propertiesOffered = new StringBuilder();
            
            for(Property property : tradeOffer.getPropertiesOffered()){
                propertiesOffered.append(property.getTileDisplayName()).append(", ");
            }
            if(propertiesOffered.length() > 3){
                propertiesOffered  = new StringBuilder(propertiesOffered.substring(0, propertiesOffered.length() - 2));
            }
            
            optionsPanel.add(new JLabel("<html><body>"+"Properties Offered: "+ propertiesOffered + "</body></html>"));
            StringBuilder propertiesRequested = new StringBuilder();
            
            for(Property property : tradeOffer.getPropertiesReceived()){
                propertiesRequested.append(property.getTileDisplayName()).append(", ");
            }
            
            if(propertiesRequested.length() > 3){
                propertiesRequested = new StringBuilder(propertiesRequested.substring(0, propertiesRequested.length() - 2));
            }
            
            optionsPanel.add(new JLabel("<html><body>"+"Properties Requested: " + propertiesRequested +"</body></html>"));
            
            if(tradeOffer.getTradeMoney() > 0){
                optionsPanel.add(new JLabel("Money Offered: " + tradeOffer.getTradeMoney()));               
            }else if(tradeOffer.getTradeMoney() < 0){
                optionsPanel.add(new JLabel("Money Requested: " + -tradeOffer.getTradeMoney()));
            }
            
           if(tradeOffer.getJailCard() > 0){
               optionsPanel.add(new JLabel(player1Name + " offered a get out of jail free card."));
           }else if(tradeOffer.getJailCard() < 0){
               optionsPanel.add(new JLabel(player1Name + " requested a get out of jail free card."));
           }
           
           optionsPanel.add(new JLabel(flavorText));
           
           JButton acceptOffer = new JButton("Accept Offer");
           acceptOffer.addActionListener(e -> turnController.acceptTradeOffer(player1, player2, tradeOffer));
           
           JButton declineOffer = new JButton("Decline Offer");
           declineOffer.addActionListener(e -> turnController.declineTradeOffer(player1, player2, tradeOffer));
           
           JButton counterOffer = new JButton("Counter Offer");
           counterOffer.addActionListener(e -> turnController.counterOffer(player1, player2, tradeOffer));
           
           optionsPanel.add(acceptOffer);
           optionsPanel.add(declineOffer);
           optionsPanel.add(counterOffer);
        }
        
        optionsPanel.validate();
        optionsPanel.repaint();
        cardLayout.show(mainPanel, "Trade Panel");
    }
    
    //Present the result of the trade offer
    @Override
        public void showResultOfTradeOffer(int option, String flavorText, Player player1, Player player2){
            optionsPanel.removeAll();
            optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
            optionsPanel.add(new JLabel(flavorText));
            
            if(option == 1 || option == 3){
                JButton endTrade = new JButton("End Trade");
                optionsPanel.add(endTrade);
                endTrade.addActionListener(e -> turnController.endUseCase());
            }else if(option == 2){
                JButton counterOffer = new JButton("Make Counter Offer");
                optionsPanel.add(counterOffer);
                
                counterOffer.addActionListener(e -> turnController.startTrade(player2, player1));
            }
            
            optionsPanel.validate();
            optionsPanel.repaint();
            cardLayout.show(mainPanel, "Trade Panel");
        }    
}
