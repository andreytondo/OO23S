package view;

import controller.Controller;
import persistence.utils.Entity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Abstract frame to provide CRUD operations for a generic entity type.
 *
 * @param <T> The type of the entity's identifier.
 * @param <E> The entity type to manage.
 */
public abstract class CrudFrame<T, E extends Entity<T>> extends JFrame {

    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JPanel formPanel;
    protected JButton insertButton, updateButton, deleteButton;
    protected List<E> entities;

    public CrudFrame(String title) {
        setTitle(title);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        initFormPanel();
        initButtons();
        initTable();

        // Load initial data
        SwingUtilities.invokeLater(this::loadData);

        // Ensure the frame is visible
        setVisible(true);
    }

    private void initTable() {
        // Table for listing entities
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(getColumns());

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void initFormPanel() {
        // Form for insert/update
        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buildForm(formPanel);
        add(formPanel, BorderLayout.NORTH);
    }

    private void initButtons() {
        // Buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout());
        insertButton = createButton("Inserir", e -> insert());
        updateButton = createButton("Atualizar", e -> update());
        deleteButton = createButton("Deletar", e -> delete());


        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(createButton("Voltar", e -> dispose()));

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    /**
     * Template method to load data into the table. This method provides a
     * default implementation for fetching entities and updating the table model.
     */
    protected void loadData() {
        tableModel.setRowCount(0);
        this.entities = getAll();
        entities.forEach(entity -> tableModel.addRow(mapEntityToRow(entity)));
    }

    /**
     * Method to retrieve all entities to be displayed in the table.
     * This should be implemented by subclasses to fetch data from the appropriate source.
     *
     * @return List of entities.
     */
    protected List<E> getAll() {
        return getController().loadData();
    }

    /**
     * Delete the selected entity.
     */
    protected void delete() {
        getController().delete(getSelectedEntity());
        loadData();
    }

    /**
     * Get the selected entity from the table.
     */
    protected E getSelectedEntity() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return null;
        }

        return entities.get(selectedRow);
    }

    /**
     * Map an entity to a row in the table model.
     *
     * @param entity The entity to be mapped.
     * @return Array representing the row in the table.
     */
    protected abstract Object[] mapEntityToRow(E entity);

    /**
     * Build the form panel with input fields for entity properties.
     *
     * @param formPanel JPanel to add input fields.
     */
    protected abstract void buildForm(JPanel formPanel);

    /**
     * Insert a new entity based on the input fields.
     */
    protected abstract void insert();

    /**
     * Update the selected entity based on the input fields.
     */
    protected abstract void update();

    /**
     * Get an instance of the controller
     */
    protected abstract Controller<T, E> getController();

    protected abstract Object[] getColumns();

}
