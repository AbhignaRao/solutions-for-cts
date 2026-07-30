/*
  Exercise 10 - Office Space Rental App
  Goal: hold JSX in variables, render an image, and colour the rent with a
  conditional inline style - red below 60,000 and green at 60,000 or above.
*/

import workspaceImage from './workspace.svg';

const RENT_THRESHOLD = 60000;

const featured = {
  name: 'DBS Business Centre',
  rent: 50000,
  address: 'Anna Salai, Chennai',
};

const listings = [
  { name: 'DBS Business Centre', rent: 50000, address: 'Anna Salai, Chennai' },
  { name: 'Skyline Co-working Loft', rent: 75000, address: 'Indiranagar, Bengaluru' },
  { name: 'Tech Park Private Cabin', rent: 58000, address: 'Gachibowli, Hyderabad' },
  { name: 'Harbour View Suite', rent: 120000, address: 'Lower Parel, Mumbai' },
];

const rupees = (value) => `Rs. ${value.toLocaleString('en-IN')}`;

const rentStyle = (rent) => ({
  color: rent < RENT_THRESHOLD ? 'red' : 'green',
  fontWeight: 600,
});

function OfficeRental() {
  /* A JSX element stored in a plain variable, then dropped into the tree. */
  const headline = 'Office Space';

  const preview = (
    <img
      src={workspaceImage}
      alt="Desk and chair in a rented office beside a window"
      style={{
        width: '100%',
        maxWidth: '440px',
        border: '1px solid var(--rule)',
        borderRadius: 'var(--radius)',
        display: 'block',
      }}
    />
  );

  return (
    <div className="panel">
      <span className="tag">JSX + inline styles</span>
      <h2 className="panel__title">{headline}, at an affordable range</h2>

      {preview}

      <h3 className="panel__title" style={{ marginTop: '20px' }}>
        Featured: {featured.name}
      </h3>
      <p style={rentStyle(featured.rent)}>Rent: {rupees(featured.rent)}</p>
      <p className="card__meta">Address: {featured.address}</p>

      <h3 className="panel__title" style={{ marginTop: '24px' }}>
        All available spaces
      </h3>
      <div className="grid">
        {listings.map((office) => (
          <div className="card" key={office.name}>
            <h4 className="card__title">{office.name}</h4>
            <p style={rentStyle(office.rent)}>{rupees(office.rent)} / month</p>
            <p className="card__meta">{office.address}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default OfficeRental;
